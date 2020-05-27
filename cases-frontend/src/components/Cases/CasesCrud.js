import React, { Component } from 'react';
import Menu from '../Menu/Menu';
import Cases from './Cases';
import Case from './Case';
import CaseService from '../../services/cases/cases.service';
import { withSnackbar } from 'notistack';


const { StateView, findCasesList, save } = CaseService;


class CasesCrud extends Component {
  
  constructor(props) {
    super(props);
    this.state = {
      selectedCase: undefined,
      configSearch: {
        field: undefined,
        word: undefined
      },
      listCases: {
        content: [],
        number: 0,
        size: 10,
        totalElements: 0,
        totalPages: 0
      },
      loadingData: false,
      stateView: StateView.LIST
    }
    this.search = this.search.bind(this);
    this.loadList = this.loadList.bind(this);
    this.view = this.view.bind(this);
    this.edit = this.edit.bind(this);
    this.newCase = this.newCase.bind(this);
    this.saveCase = this.saveCase.bind(this);
    this.list = this.list.bind(this);
  }

  componentDidMount() {
    this.loadList();
  }

  loadList() {
    const { number, size } = this.state.listCases;
    const { configSearch } = this.state;
    this.setState(state => ({
      loadingData: true
    }));
    findCasesList(configSearch.field, configSearch.word, number, size, (data) => {
      this.setState(state => ({
        listCases: data,
        loadingData: false
      }));
    });
  }

  search(field, word, number, size) {
    this.setState(state => ({
      configSearch: {
        field: field,
        word: word
      },
      listCases: {
        ...state.listCases,
        number: number,
        size: size,
      }
    }), () => {
      this.loadList();
    });
  }

  isStateView(stateView) {
    return this.state.stateView === stateView;
  }

  changeStateView(stateView) {
    this.setState(state => ({
      stateView
    }));
  }

  showSuccess(msg) {
    this.props.enqueueSnackbar(msg, { variant : "success"})
  }

  saveCase(entityCase, error) {
    this.setState(state => ({
      loadingData: true
    }));
    save(entityCase, (data) => {
      //save success
      this.setState(state => ({
        ...state,
        selectedCase: data,
        stateView: StateView.VIEW,
        loadingData: false,
        listCases: {
          ...state.listCases,
          content: [data, ...state.listCases.content.filter( item => item.id !== data.id)],
          totalElements: this.isStateView(StateView.NEW) ? state.listCases.totalElements + 1 : state.listCases.totalElements,
        },
      }), () => {
        this.showSuccess("Success.");
      });
    }, (errors) => {
      console.log("crud save", errors);
      this.setState(state => ({
        loadingData: false
      }));
      error(errors);
    });
  }

  edit(selectedCase) {
    this.setState(state => ({
      ...state,
      selectedCase: selectedCase,
      stateView: StateView.EDIT
    }));
  }

  view(selectedCase) {
    this.setState(state => ({
      ...state,
      selectedCase,
      stateView: StateView.VIEW
    }));
  }

  newCase() {
    this.setState(state => ({
      ...state,
      selectedCase : {},
      stateView: StateView.NEW
    }));
  }

  list() {
    this.setState(state => ({
      selectedCase : undefined,
      stateView: StateView.LIST
    }));
  }

  render() {
    const { stateView, selectedCase, listCases, loadingData } = this.state;
    return (
      <Menu>
         {this.isStateView(StateView.LIST) ? 
          <Cases 
            listCases={listCases} 
            search={this.search} 
            loadingData={loadingData}
            edit={this.edit}
            view={this.view}
            newCase={this.newCase}
          >
          </Cases> 
          :
          <Case 
            selectedCase={selectedCase} 
            stateView={stateView} 
            save={this.saveCase}
            back={this.list}
            loadingData={loadingData}
          >
          </Case>
        } 
      </Menu>
    );
  }
}

export default withSnackbar(CasesCrud);
