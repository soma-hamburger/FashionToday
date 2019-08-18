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

export const Schedule = styled.div`
  border: 1px solid black;
  padding: 10px;
`
export const DayGrid = styled.div`
  display: grid;
  grid-template-columns: 1fr max-content;
`

export const DayLookView = styled.div`
  display: grid;
  grid-template-columns: 180px auto;
`
export const DayLookImageView = styled.div`
  width: 178px;
  height: 235px;
  border: 1px solid gray;
  text-align: center;
`
export const DayLookImageWrapper = styled.div`
  display: inline-block;
  width: 170px;
  height: 210px;
  border: 1px solid gray;
`
export const DayLookInfoView = styled.div`
  border: 1px solid gray;
`
export const DayLEView = styled.div`
  border: 1px solid gray;
  grid-column: 1 / 3;
`
export const DayComment = styled.div`
  padding: 5px;
  border-radius: 3px 3px 0px 0px;
  border: 1px solid gray;
  max-width: 300px;
  font-size: 13px;
`
export const Recommend = styled.div`
  font-size: 13px;
  padding-left 10px;
`