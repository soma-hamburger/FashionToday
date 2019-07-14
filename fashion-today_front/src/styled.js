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

export const Title = styled.h1`
  margin-bottom: 0.5em;
  font-size: 1.1em;
  cursor: pointer;
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

export const Class = styled.div`
  cursor: pointer;
  border: 1px solid black;
  color: ${props => props.color || "blue"};
  text-decoration: none;
  padding-left: 0.5em;
  margin: ${props => props.small ? "0" : "0.3em"};
  font-size: ${props => props.small ? "0.5em" : "1.2em"};
`


export const Button = styled.button`
  display: ${props => props.small ? "inline" : "block"};
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
  padding: 1em;
  border: 1px solid black;
  text-decoration: none;
`

export const CItem = styled.div`
  margin-top: 10px;
  padding: 3px;
  border: 1px solid gray;
`

export const ClosetTable = styled.div`
  border: 1px solid black;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
`

export const ClothesPicture = styled.img`
  cursor: pointer;
  border: 1px solid black;
  margin: 5px;
  height: 150px;
`