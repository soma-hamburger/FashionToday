import styled from 'styled-components';

export const Cal = styled.div`
  width: 95%;
  max-width: 800px;
  margin: 0 auto;
`
export const CalFrame = styled.div`
  border: 1px solid gray;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
`

export const CalNav = styled.div`
  display: grid;
  grid-template-columns: 100px auto 100px;
  margin-bottom: 10px;
`

export const CalTitle = styled.div`
  font-size: 20px;
  text-align: center;
`
export const DateName = styled.div`
  border: 1px solid gray;
  font-size: 18px;
  height: 20px;
  text-align: center;
  margin-bottom: 10px;
`
export const DayNum = styled.b`
  display: block;
  font-size: 17px;
`
export const CalDay = styled.div`
  min-height: 50px;
  padding: 5px;
  font-size: 13px;
  font-color: black;
`