import styled from 'styled-components';
import {Link} from 'react-router-dom';

export const Pages = styled.div`
  width: 700px;
  margin: 0 auto;
  padding: 5px;
  padding-top: 10px;
  border: 1px solid black;
  margin-top: 3em;
  color: black;
`

export const Bar = styled.div`
  width: 100%;
  background-color: gray;
  color: white;
  height: 1.1em;
  position: fixed;
  top: 0;
  left: 0;
  padding: 0.5em;
`

export const SLink = styled(Link)`
  display: block;
  margin-bottom: 0.5em;
  color: ${props => props.color || "blue"};
  text-decoration: none;
  font-size: ${props => props.small ? "0.8em" : "1.2em"};
`

export const A = styled(Link)`
  text-decoration: none;
`

export const CalTable = styled.div`
  display: grid;
  grid-template-columns: repeat(7, 1fr);
`

export const LItem = styled.div`
  margin: 3px;
  border: 1px solid black;
  text-decoration: none;
`

export const CItem = styled.div`
  margin: 3px;
  border: 1px solid gray;
`