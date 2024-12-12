import styled from "styled-components";

export const Conteiner = styled.div`
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100vh;
  background: #000;

  .conteiner {
    display: flex;
    width: 50%;
    height: 100%;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }
  
  .content {
    display: flex;
    min-width: 440px;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 0px 10px;
    background-color: #000;
    border-radius: 10px;
    padding: 30px;
    box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.25);

    h1,
    h3 {
      color: ${(props) => props.theme.colors.h1};
    }
    h1,
    h3,
    p {
      font-family: ${(props) => props.theme.fonts.Montserrat};
    }

    p {
      font-size: ${(props) => props.theme.fonts.p};
      color: ${(props) => props.theme.colors.p};
    }

    h1 {
      font-size: ${(props) => props.theme.fonts.h1};
      margin-bottom: 2rem;
      font-weight: ${(props) => props.theme.fonts.bold};
    }

    h3 {
      font-size: ${(props) => props.theme.fonts.h3};
      font-weight: ${(props) => props.theme.fonts.semibold};
    }

    span {
      margin-top: 1rem;
      color: ${(props) => props.theme.colors.p};
      a {
        color: ${(props) => props.theme.colors.background_button};
      }
    }
  }

  .image{
    display: flex;
    width: 50%;
    height: 100%;
    justify-content: center;
    align-items: center;
    align-self: flex-end;
    
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 10px;
      border-radius:  100px 0px 0px 100px;
    }
  }
`;


export const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;

  label {
    margin-top: 1rem;
    color: ${(props) => props.theme.colors.label};
    font-weight: ${(props) => props.theme.fonts.regular};
  }

  input {
    width: 100%;
    margin-top: 0.5rem;
    padding: 0.7rem;
    color: #fff;
    border: 1px solid ${(props) => props.theme.colors.border};
    border-radius: 5px;
    outline: none;
    font-size: ${(props) => props.theme.fonts.p};
    background-color: #000; /* Set background color to black */

    &:focus {
      border: 1px solid ${(props) => props.theme.colors.border};
    }

    &::placeholder {
      font-family: ${(props) => props.theme.fonts.Montserrat};
      color: ${(props) => props.theme.colors.placeholder};
      font-weight: ${(props) => props.theme.fonts.regular};
    }
  }

  .erro {
    color: #ff0000 !important;
    font-size: ${(props) => props.theme.fonts.erro} !important;
    margin-top: 0.8rem;
    align-self: center;
  }

  button {
    width: 100%;
    margin-top: 1rem;
    padding: 0.5rem;
    background-color: ${(props) => props.theme.colors.background_button};
    border-radius: 5px;
    color: #000;
    cursor: pointer;
    border: none;
    font-weight: ${(props) => props.theme.fonts.bold};
  }
`;
