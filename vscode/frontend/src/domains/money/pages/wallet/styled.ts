import styled from "styled-components";
import { ErrorMessage } from "formik";

export const Header = styled.div`
  width: 80%;
  min-height: 100px;
  background-color: #0F0F0F;
  position: relative;
  padding: 20px;
  border-radius: 23.31px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: white;
  margin-bottom: 20px;
  box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);

  h1 {
    font-size: ${props => props.theme.fonts.h1};
    margin: 0;
  }

  p {
    margin-top: 10px;
    font-size: ${props => props.theme.fonts.p};
  }

  img{
    position: absolute;
    width: 80px;
    height: 80px;
    border-radius: 50%;
    right: 10px;
    bottom: 10px;
  }

  
`;

export const Conteiner = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  width: 100%;
  height: 100vh;

  .title {
    display: flex;
    align-items: center;
    width: 100%;
    padding: 1rem;
    color: #fff
  }

  .form {
    display: flex;
    flex-direction: column;
    width: 100%;
    max-width: 400px;
    padding: 0px 1.5rem;

    label {
      margin-top: 1rem;
      color: #fff;
    }

    input {
      margin-top: 0.5rem;
      padding: 0.5rem;
      border: 1px solid ${(props) => props.theme.colors.border};
      border-radius: 32px;
      ::placeholder {
        color: ${(props) => props.theme.colors.color};
      }
      color: #fff;
      background-color: #0F0F0F;
    }

    .radio_btn {
      display: flex;
      flex-direction: column;
      align-items: flex-start;
      justify-content: space-between;
      margin-top: 1rem;;

      label {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        margin-top: 0.5rem;
        color: #fff;

        input {
          display: flex;
          justify-content: center;
          align-items: center;
          margin-right: 0.5rem;
          width: 100%;
          height: 100%;
        }
      }
    }

    button {
      margin-top: 1rem;
      padding: 0.5rem;
      background-color: #EFD900;
      border-radius: 100px;
      color: #000;
      cursor: pointer;
      border: none;
      font-size: ${(props) => props.theme.fonts.p};
      font-weight: ${(props) => props.theme.fonts.regular};
    }

    .erro {
      color: red;
      font-size: 14px;
      margin-top: 4px;
    }
  }
`;

export const StyledErrorMessage = styled(ErrorMessage)`
  color: red;
  font-size: 14px;
  margin-top: 4px;
`;
