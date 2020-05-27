import React from 'react';
import useStyles from '../Cases/Cases.styles';
import TextField from '@material-ui/core/TextField';
import { Paper, Box, Container, RadioGroup, FormControlLabel, FormLabel, FormControl, Radio, Button, Chip, IconButton, ButtonGroup, Divider } from '@material-ui/core';
import SaveIcon from '@material-ui/icons/Save';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import AddCircleIcon from '@material-ui/icons/AddCircle';
import CaseService from '../../services/cases/cases.service';
import Loading from '../base/Loading/Loading';

const { StateView, isView } = CaseService;


const Case = (props) => {
  const classes = useStyles();

  const { save, back, selectedCase, stateView, loadingData  } = props;

  const [alteredCase, setAlteredCase] = React.useState(selectedCase);

  const [errors, setErrors] = React.useState({});

  const [tagToAdd, setTagToAdd] = React.useState("");

  const performSave = () => {
    setErrors({});
    save(alteredCase, (saveErrors) => {
      setErrors(saveErrors);
    });
  }

  const handleChange = (field, value) => {
    setAlteredCase({
      ...alteredCase,
      [field]: value,
    });
  }

  const handleDeleteTag = (tagToDelete) => {
    setAlteredCase({
      ...alteredCase,
      ['tags']: getTags().filter((tag) => tag !== tagToDelete),
    });
    setErrors({});
  }

  const handleAddTag = () =>  {
    if (!tagToAdd || tagToAdd === "") {
      setErrors({
        ...errors,
        ['tags']: "Tag can not be empty"
      });
      return;
    }
    if (getTags().filter((tag) => tag === tagToAdd).length > 0) {
      setErrors({
        ...errors,
        ['tags']: `Tag ${tagToAdd} already exists`
      });
      return;
    }
    setAlteredCase({
      ...alteredCase,
      ['tags']: [...getTags(), tagToAdd],
    });
    setTagToAdd("");
    setErrors({});
  }

  const getTags = () => {
    return alteredCase['tags'] ? alteredCase['tags'] : [];
  }

  const isHaveError = (field) => {
    return errors && errors[field] != null;
  }

  const getError = (field) => {
    return errors && errors[field] != null ? errors[field] : '';
  }

  return (
    <Container maxWidth="xl">
      <Paper className={classes.rootForm}>
        <h2 className={classes.formTitle}>Case register</h2>
        <Box component="div">
            <TextField 
              className={classes.input33}
              error={isHaveError('folder')}
              id="folder" 
              label="Folder"
              defaultValue=""
              helperText={getError('folder')}
              value={alteredCase['folder']} onChange={(event) => handleChange('folder', event.target.value)}
              style={{with: '50%'}}
              disabled={isView(stateView)}
            />
            <TextField
              className={classes.input33}
              error={isHaveError('clients')}
              id="clients"
              label="Clients"
              defaultValue=""
              helperText={getError('clients')}
              value={alteredCase['clients']} onChange={(event) => handleChange('clients', event.target.value)}
              disabled={isView(stateView)}
            />
            <TextField
              className={classes.input33}
              error={isHaveError('title')}
              id="title"
              label="Title"
              defaultValue=""
              helperText={getError('title')}
              value={alteredCase['title']} onChange={(event) => handleChange('title', event.target.value)}
              disabled={isView(stateView)}
            />
            <TextField
              error={isHaveError('description')}
              id="description"
              label="Description"
              defaultValue=""
              fullWidth
              helperText={getError('description')}
              value={alteredCase['description']} onChange={(event) => handleChange('description', event.target.value)}
              disabled={isView(stateView)}
            />
          </Box>
          <Box component="div">
            <TextField
              error={isHaveError('comments')}
              id="comments"
              label="Comments"
              defaultValue=""
              multiline
              rowsMax={4}
              fullWidth
              helperText={getError('comments')}
              value={alteredCase['comments']} onChange={(event) => handleChange('comments', event.target.value)}
              disabled={isView(stateView)}
            />
        </Box>
        <Box component="div">
            <TextField
              className={classes.input33}
              error={isHaveError('responsible')}
              id="responsible"
              label="Responsible"
              defaultValue=""
              helperText={getError('responsible')}
              value={alteredCase['responsible']} onChange={(event) => handleChange('responsible', event.target.value)}
              disabled={isView(stateView)}
            />

            <FormControl >
              <FormLabel component="legend">Access</FormLabel>
              <RadioGroup row 
                aria-label="Access" 
                name="Access" 
                value={alteredCase['access']} onChange={(event) => handleChange('access', event.target.value)}
              >
                <FormControlLabel disabled={isView(stateView)} value="PRIVATE" control={<Radio />} label="Private" />
                <FormControlLabel disabled={isView(stateView)} value="PUBLIC" control={<Radio />} label="Public" />
              </RadioGroup>
            </FormControl>
        </Box>
        <Box component="div">
          <TextField
              error={isHaveError('tags')}
              id="tags"
              label="Tags"
              defaultValue=""
              helperText={getError('tags')}
              value={tagToAdd} onChange={(event) => setTagToAdd(event.target.value)}
              disabled={isView(stateView)}
            />
          <IconButton color="primary" className={classes.addTagButton} aria-label="directions"
            onClick={handleAddTag} disabled={isView(stateView)}
          >
            <AddCircleIcon />
          </IconButton>
        </Box>
        <Box component="div">
          <Box component="ul" className={classes.ulChip}>
            {getTags().map((tag) => {
              return (
                <li key={tag}>
                  <Chip
                    label={tag}
                    onDelete={ () => handleDeleteTag(tag) }
                    className={classes.chip}
                    disabled={isView(stateView)}
                  />
                </li>
              );
            })}
          </Box>
        </Box>
        <Divider orientation="horizontal" />
        <Box component="div" className={classes.buttonBar}>

          <Button
            variant="contained"
            size="large"
            className={classes.button}
            startIcon={<ArrowBackIcon />}
            onClick={back}
          >
            Back
          </Button>

          <Button
            variant="contained"
            color="primary"
            size="large"
            className={classes.button}
            startIcon={<SaveIcon />}
            onClick={performSave}
            disabled={isView(stateView)}
          >
            Save
          </Button>
        </Box>
        <Loading loadingData={loadingData}/>
      </Paper>
    </Container>
  );
}

export default Case;
