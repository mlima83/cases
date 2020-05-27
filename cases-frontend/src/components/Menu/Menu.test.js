import React from 'react';
import { cleanup, render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import Menu from './Menu';

describe('<Menu />', () => {
  afterEach(cleanup);

  test('it should mount', () => {
    const { getByTestId } = render(<Menu />);
    const home = getByTestId('Menu');

    expect(home).toBeInTheDocument();
  });
});