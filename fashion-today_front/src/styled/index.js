import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const SLink = styled(Link)`
  display: block;
  color: ${(props) => props.color || 'black'};
  text-decoration: none;
  font-size: ${(props) => (props.small ? '0.8em' : '1.2em')};
`;

export const A = styled(Link)`
  text-decoration: none;
`;

export const Image = styled.img`
  cursor: pointer;
  width: 100%;
  height: 100%;
  margin: 0px;
  border-radius: ${(props) => props.br};
`;

export const Pages = styled.div`
  width: 1000px;
  margin: 0 auto;
  border: 1px solid gray;
  margin-top: 70px;
  color: black;
  @media (max-width: 1180px) {
    width: 95%;
  }
`;

export const MainIcon = styled(SLink)`
  width: 50px;
  height: 50px;
  margin-right: 10px;
  border: 1px solid gray;
  font-size: 14px;
`;

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
`;

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
`;

export const Navigation = styled.div`
  border: 1px solid gray;
  height: 50px;
  padding: 0;
  min-width: 500px;
`;

export const NavButton = styled(Link)`
  display: inline-block;
  border: 1px solid gray;
  height: 50px;
  line-height: ${(props) => (props.linenum ? `${50 / props.linenum}px` : '50px')};
  margin-left: 15px;
  font-size: 18px;
  text-decoration: none;
  text-align: center;
  min-width: 80px;
  color: ${(props) => (props.iscurrent ? 'blue' : 'black')};
  border-bottom: ${(props) => (props.iscurrent ? '3px solid white' : '1px solid gray')};
`;

export const Title = styled.h1`
  margin-bottom: 0.5em;
  font-size: 1.1em;
  cursor: pointer;
`;
export const Button = styled.button`
  margin: 0px;
  display: ${(props) => (props.small ? 'inline-block' : 'block')};
  color: ${(props) => props.color || 'blue'};
  text-decoration: none;
  font-size: ${(props) => (props.small ? '0.8em' : '1.2em')};
`;
export const Input = styled.input`
  margin-top: 10px;
  width: ${(props) => props.width};
  font-size: 14px;
`;
export const List = styled.div`
  display: grid;
  padding: 0;
`;

export const PreviewContainer = styled.div`
  border: 1px solid black;
  width: 200px;
  height: 270px;
`;

export const UserProfile = styled.div`
  border: 1px solid black;
  height: 38px;
  display: grid;
  grid-template-columns: 35px auto;
`;

export const LookImage = styled.div`
  border: 1px solid black;
  width: 198px;
  height: 230px;
`;

export const ProfileImage = styled.div`
  border: 1px solid black;
  width: 30px;
  height: 30px;
  margin: 2px;
  display: inline-block;
`;

export const UserName = styled.div`
  display: inline-block;
  width: 158px;
  height: 30px;
  border: 1px solid black;
  font-size: 30px;
  margin: 2px;
`;
