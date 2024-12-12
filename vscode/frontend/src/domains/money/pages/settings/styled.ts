import { ErrorMessage } from "formik";
import styled from "styled-components";

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
  justify-content: flex-start;
  align-items: flex-start;
  gap: 20px;
  width: 100%;
  height: 100%;

  h1 {
    font-size: ${(props) => props.theme.fonts.h1};
    font-weight: ${(props) => props.theme.fonts.bold};
    display: flex;
    justify-content: center;
    width: 100%;
  }

  h1 {
    margin-bottom: 1rem;
  }

  h3 {
    font-size: ${(props) => props.theme.fonts.h3};
    font-weight: ${(props) => props.theme.fonts.bold};
    margin-top: 1rem;
    color: #fff;
  }

  .form {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: 40%;

    label {
      font-size: ${(props) => props.theme.fonts.p};
      font-weight: ${(props) => props.theme.fonts.bold};
      display: flex;
      flex-direction: column;
      color: #fff;
    }

    input {
      padding: 0.5rem;
      border: 1px solid #2F2F2F;
      border-radius: 32px;
      width: 50%;
      font-size: ${(props) => props.theme.fonts.p};
      color: #fff;
      background-color: #0F0F0F;

      &:focus {
        border: 1px solid ${(props) => props.theme.colors.border};
      }
    }

    button {
      padding: 1rem 5rem;
      border: none;
      border-radius: 100px;
      width: 43%;
      background-color: #EFD900;
      color: #000;
      font-size: ${(props) => props.theme.fonts.p};
      font-weight: ${(props) => props.theme.fonts.regular};
      cursor: pointer;
    }

    .delete {
      background-color: #fff;
    }

    .info {
      font-size: ${(props) => props.theme.fonts.p};
      font-weight: ${(props) => props.theme.regular};
      color: #e92121;
    }
  }
`;

export const StyledErrorMessage = styled(ErrorMessage)`
  color: red;
  font-size: 14px;
  margin-top: 4px;
`;
