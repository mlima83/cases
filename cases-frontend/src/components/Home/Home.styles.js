import { makeStyles, useTheme } from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
    },
    container: {
      maxHeight: 440,
      padding: 15
    },
  }));

  export default useStyles;