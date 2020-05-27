import React from 'react';
import { CircularProgress } from "@material-ui/core";
import useStyles from "./Loading.styles";

const Loading = (props) => {
    const classes = useStyles();
    const { loadingData } = props;
    if (loadingData) {
      return (
          <div className={classes.rowloading}>
            <CircularProgress color="secondary" />
          </div>
      );
    }
    return null;
  }

  export default Loading;