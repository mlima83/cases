import { makeStyles, useTheme } from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
    },
    listRoot: {
      width: '100%',
    },
    container: {
      maxHeight: 440,
    },
    rowloading: {
      width: '100%',
      display: 'flex',
      justifyContent: 'center',
      paddingBottom: '10px',
    },
    searchRoot: {
      padding: '2px 4px',
      display: 'flex',
      alignItems: 'center',
      width: '100%',
      '& .MuiSelect-select' : {
        minWidth: 100,
      },
    },
    input: {
      marginLeft: theme.spacing(2),
      marginRight: theme.spacing(1),
      flex: 1,
    },
    iconButton: {
      padding: 10,
    },
    divider: {
      height: 28,
      margin: 4,
    },
    formControlField: {
      margin: theme.spacing(2),
      minWidth: 100,
    },
    rootForm: {
      '& .MuiTextField-root': {
        margin: theme.spacing(1),
      },
      '& .MuiFormControl-fullWidth':{
        width: '98%',
      },
    },
    formTitle: {
      paddingTop: 5,
      paddingLeft: 10,
    },
    button: {
      margin: theme.spacing(1),
    },
    buttonBar: {
      width: '100%',
      textAlign: 'center',
    },
    ulChip: {
      display: 'flex',
      justifyContent: 'left',
      flexWrap: 'wrap',
      listStyle: 'none',
      padding: theme.spacing(0.5),
      margin: 0,
      width: '100%',
    },
    chip: {
      margin: theme.spacing(0.5),
    },
    addTagButton: {
      verticalAlign: 'bottom',
    },
    input33: {
      width: '30%'
    },
  }));

  export default useStyles;