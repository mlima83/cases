import React from 'react';
import useStyles from '../Home/Home.styles';
import Menu from '../Menu/Menu';
import { Container, Paper } from '@material-ui/core';


const Home = () => {
  const classes = useStyles();
  return (
    <Menu>
      <Container maxWidth="xl"  className={classes.root}> 
        <Paper className={classes.container}>
            <p>This project has the purpose of technical evaluation for the selection process organized by the company DBR.</p>
            <p>The Case app performs registration and case management.</p>
        </Paper>
      </Container>
    </Menu>
  );
}

export default Home;
