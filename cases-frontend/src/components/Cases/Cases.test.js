import React from 'react';
import { cleanup, render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import Cases from './Cases';

describe('<Cases />', () => {
  afterEach(cleanup);

  test('it should mount', () => {
    const { getByTestId } = render(<Cases />);
    const cases = getByTestId('Cases');

    expect(cases).toBeInTheDocument();
  });
});