import styled from "styled-components";

export const Conteiner = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  width: 100%;
  height: 100vh;

  .board {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    width: 80%;
    height: 100%;
    padding: 2rem;
  }
`;

export const SearchBar = styled.input`
  width: 200%;
  max-width: 400px;
  padding: 10px 15px;
  font-size: 16px;
  border-radius: 23.31px;
  border: 1px solid #ccc;
  margin-bottom: 20px;
  background-color: #2d2d2d;
  color: white;

  &:focus {
    outline: none;
    border-color: #4caf50;
  }
`;

export const WalletCard = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  margin: 10px 0;
  border-radius: 8px;
  background-color: #2d2d2d;
  color: white;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);

  .wallet-info {
    display: flex;
    flex-direction: column;

    h3 {
      margin: 0;
      font-size: 18px;
    }

    p {
      margin: 5px 0 0;
      font-size: 14px;
      color: #aaa;
    }
  }

  .menu {
    display: flex;
    align-items: center;
  }

  &:hover {
    background-color: #333;
  }
`;

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

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 400px;
  padding: 0px 1.5rem;

  label {
    margin-top: 1rem;
    color: ${(props) => props.theme.colors.secundary};
  }

  input {
    margin-top: 0.5rem;
    padding: 0.5rem;
    border: 1px solid ${(props) => props.theme.colors.text};
    border-radius: 8px;
    ::placeholder {
      color: ${(props) => props.theme.colors.color};
    }

    :invalid {
      border-color: red;
    }

    :valid {
      border-color: green;
    }

    :required {
      border-color: blue;
    }
  }

  input[type="text"]:focus {
    border: 2px solid ${(props) => props.theme.colors.primary};
  }

  button {
    margin-top: 1rem;
    padding: 0.5rem;
    background-color: ${(props) => props.theme.colors.primary};
    border-radius: 8px;
    color: #fff;
    cursor: pointer;
    border: none;
  }
`;
