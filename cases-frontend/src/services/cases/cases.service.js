import axios from 'axios';

const URL_API = process.env.URL_API;

const StateView = {
    LIST: "LIST", 
    VIEW: "VIEW", 
    EDIT: "EDIT", 
    NEW: "NEW"
}


const CaseService =  {
    findCasesList : (fieldSearch, wordSearch, pageNumber, pageSize, success) => {
        axios.get(`${URL_API}/cases/search`, {
            params: {
                [fieldSearch]: wordSearch,
                page: pageNumber,
                size: pageSize
            }}).then(res => {
                console.log(res);
                if (res) {
                    success(res.data);
                }
            });
    },

    save : (entityCase, success, error) => {
        const errors = {};
        if (!entityCase) {
            errors['global'] = 'Case can not be empty';
        }
        if (entityCase.folder && entityCase.folder.length > 40) {
            errors['folder'] = 'Folder maximum characters 40';
        }
        if (!entityCase.clients || entityCase.clients === '') {
            errors['clients'] = 'Clients can not be empty';
        }
        if (!entityCase.title || entityCase.title === '') {
            errors['title'] = 'Title can not be empty';
        }
        if (!entityCase.responsible || entityCase.responsible === '') {
            errors['responsible'] = 'Responsible can not be empty';
        }
        if (errors && Object.keys(errors).length > 0) {
            return error(errors);
        }

        axios.post(`${URL_API}/cases`, entityCase)
            .then(res => {
                console.log(res);
                if (res) {
                    success(res.data);
                }
            })
            .catch(err => {
                if (err && err.response.data && err.response.data.errors) {
                    err.response.data.errors.map(erro => {
                        errors[erro.key] = erro.message;
                    });
                    error(errors);
                }
            });
    },

    StateView,

    isEdit: (stateView) => {
        return StateView.EDIT === stateView;
    },

    isView: (stateView) => {
        return StateView.VIEW === stateView;
    },

    isList: (stateView) => {
        return StateView.LIST === stateView;
    },
};

export default CaseService;