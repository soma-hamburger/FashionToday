import styled from 'styled-components';
import {Link} from 'react-router-dom';

export const SLink = styled(Link)`
  display: block;
  color: ${props => props.color || "black"};
  text-decoration: none;
  font-size: ${props => props.small ? "0.8em" : "1.2em"};
`

export const Pages = styled.div`
  width: 1000px;
  margin: 0 auto;
  border: 1px solid gray;
  margin-top: 70px;
  color: black;
  @media (max-width: 1180px) {
    width: 95%;
  }
`

export const MainIcon = styled(SLink)`
  width: 50px;
  height: 50px;
  margin-right: 10px;
  border: 1px solid gray;
  font-size: 14px;
`

export const Bar = styled.div`
  width: 100%;
  background-color: #967BDC;
  color: white;
  height: 50px;
  position: fixed;
  top: 0;
  left: 0;
  padding-top: 5px;
  padding-bottom: 5px;
`

export const BarInterface = styled.div`
  display: grid;
  grid-template-columns: 60px auto 130px;
  width: 1000px;
  margin: 0 auto;
  border: 1px solid gray;
  height: 50px;
  @media (max-width: 1180px) {
    width: 95%;
  }
` 

export const Navigation = styled.div`
  border: 1px solid gray;
  height: 50px;
  padding: 0;
  min-width: 500px;
`

export const NavButton = styled(Link)`
  display: inline-block;
  border: 1px solid gray;
  height: 50px;
  line-height: ${props => props.linenum ? `${50 / props.linenum}px`: "50px"};
  margin-left: 15px;
  font-size: 18px;
  text-decoration: none;
  text-align: center;
  min-width: 80px;
  color: ${props => props.iscurrent ? "blue":"black"};
  border-bottom: ${props => props.iscurrent ? "3px solid white": "1px solid gray"};
`

export const Title = styled.h1`
  margin-bottom: 0.5em;
  font-size: 1.1em;
  cursor: pointer;
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