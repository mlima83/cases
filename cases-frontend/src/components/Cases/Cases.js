import React from 'react';
import useStyles from '../Cases/Cases.styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import Moment from 'moment';
import { CircularProgress, TableFooter, InputLabel, Select, MenuItem, FormControl, TextField, Menu } from '@material-ui/core';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import AddCircleIcon from '@material-ui/icons/AddCircle';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import Loading from '../base/Loading/Loading';

const columns = [
  { id: 'title', label: 'Title', minWidth: 170 },
  { id: 'folder', label: 'Folder', minWidth: 100 },
  { id: 'responsible', label: 'Responsible', minWidth: 170},
  {
    id: 'dtCreated',
    label: 'Date',
    minWidth: 170,
    align: 'center',
    format: (value) => Moment(value).format('LLLL'),
  } 
];


const SearchHeader = (props) => {
  const classes = useStyles();
  const [field, setField] = React.useState('');
  const [word, setWord] = React.useState('');
  const { performSearch, newCase } = props;

  const handleFieldChange = (event) => {
    setField(event.target.value);
  };

  const handleWordChange = (event) => {
    setWord(event.target.value);
  };

  const handleSearch = (event) => {
    performSearch(field, word);
  };

  const handleAdd = (event) => {
    newCase();
  };

  return (
    <Paper component="form" className={classes.searchRoot}>
      <FormControl className={classes.formControlField}>
        <InputLabel id="field-label">Field</InputLabel>
        <Select
          labelId="field-label"
          id="field-select"
          value={field}
          onChange={handleFieldChange}
        >
          <MenuItem value={'title'}>Title</MenuItem>
          <MenuItem value={'folder'}>Folder</MenuItem>
          <MenuItem value={'description'}>Description</MenuItem>
        </Select>
      </FormControl>
      <TextField 
        className={classes.input} 
        id="standard-search" 
        label="Search field" 
        type="search" 
        value={word}
        onChange={handleWordChange}
      />
      <IconButton className={classes.iconButton} aria-label="search"
        onClick={handleSearch}
      >
        <SearchIcon />
      </IconButton>
      <Divider className={classes.divider} orientation="vertical" />
      <IconButton color="primary" className={classes.iconButton} aria-label="directions"
        onClick={handleAdd}
      >
        <AddCircleIcon />
      </IconButton>
    </Paper>
  );
}

const Row = (props) => {
  const { rows, edit, view } = props;
  return (
    rows.map((row) => {
      return (
        <TableRow hover role="checkbox" tabIndex={-1} key={row.id}>
          {columns.map((column) => {
            const value = row[column.id];
            return (
              <TableCell key={column.id} align={column.align}>
                {column.format  ? column.format(value) : value}
              </TableCell>
            );
          })}
          <TableCell
            key={'menu'}
            align={'center'}
            style={{ minWidth: 5 }}
          >
            <SubMenu edit={() => edit(row)} view={() => view(row)}/>
          </TableCell>
        </TableRow>
      );
    })
  );
}

const SubMenu = (props) => {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);

  const { edit, view } = props;

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <>
      <IconButton
        aria-label="more"
        aria-controls="long-menu"
        aria-haspopup="true"
        onClick={handleClick}
      >
        <MoreVertIcon />
      </IconButton>
      <Menu
        id="long-menu"
        anchorEl={anchorEl}
        keepMounted
        open={open}
        onClose={handleClose}
        PaperProps={{
          style: {
            maxHeight: 100,
            width: '20ch',
          },
        }}
      >
        <MenuItem key={1} onClick={() => {edit(); handleClose();}}>
          Edit
        </MenuItem>
        <MenuItem key={2} onClick={() => {view(); handleClose();}}>
          Detail
        </MenuItem>
      </Menu>
    </>
  );
}


const Cases = (props) => {
  const classes = useStyles();
  const { content, number, size, totalElements } = props.listCases;
  const { 
    search, 
    newCase,
    edit,
    view,
    loadingData 
  } = props;
  console.log("Cases props", props);
  const handleChangePage = (event, newPage) => {
    search("", "", newPage, size);
  };

  const handleChangeRowsPerPage = (event) => {
    search("", "", 0, +event.target.value);
  };

  const performSearch = (field, word) => {
    search(field, word, 0, size);
  }

  return (
    <Paper className={classes.listRoot}>
    <h2 className={classes.formTitle}>List of cases</h2>
    <SearchHeader performSearch={performSearch} newCase={newCase}/>
    <TableContainer className={classes.container}>
      <Table stickyHeader aria-label="sticky table">
        <TableHead>
          <TableRow>
            {columns.map((column) => (
              <TableCell
                key={column.id}
                align={column.align}
                style={{ minWidth: column.minWidth }}
              >
                {column.label}
              </TableCell>
            ))}
              <TableCell
                key={'menu'}
                align={'center'}
                style={{ minWidth:  5 }}
              >
                {}
              </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
            <Row rows={content} edit={edit} view={view}/>
        </TableBody>
      </Table>
    </TableContainer>
    <TablePagination
      rowsPerPageOptions={[10, 25, 100]}
      component="div"
      count={totalElements}
      rowsPerPage={size}
      page={number}
      onChangePage={handleChangePage}
      onChangeRowsPerPage={handleChangeRowsPerPage}
    />
    <Loading loadingData={loadingData}/>
  </Paper>
  );
}

export default Cases;
