import styled from "styled-components";

export const Bar_lat = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 15%;
  height: calc(100vh - 40px);
  border-radius:  23.31px 23.31px 23.31px 23.31px;
  background-color: #0F0F0F;
  margin: 20px 0 20px 20px; /* Adds space: top, right, bottom, left */
  padding: 20px;

  h1 {
    color: #fff;
    font-size: 1.8rem;
    margin-top: 3rem;
  }
  h1 > span {
    color: ${(props) => props.theme.colors.h1};
    font-weight: bold;
  }

  .profile {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 1rem;
    width: 100%;
    margin: 3rem 0px;

    img {
      width: 100px;
      height: 100px;
      border-radius: 50%;
      border: 1px solid ${(props) => props.theme.colors.h1};
    }

    h2 {
      span{
        color: #fff;
      }
    }

    h3 {
      margin-top: 0.5rem;
      color: #000;
    }

    p {
      margin-top: 0.5rem;
      color: ${(props) => props.theme.colors.h1};
    }
  }

  .conteiner_button {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    margin-top: 3rem;
    width: 100%;
    height: 100%;
    position: relative;

    .end {
      position: absolute;
      bottom: 0px;
      margin-bottom: 1rem;
    }

    .active {
      background-color: #FFF8C5;
    }
  }

  .button,
  .conteiner_button > button {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-start;
    margin-top: 1rem;
    padding: 1rem 2.5rem;
    border-radius: 4px;
    color: #757575;
    cursor: pointer;
    border: none;
    width: 90%;
    background-color: transparent;
    text-decoration: none;
    text-align: center;

    .icon {
      width: 20px;
      height: 20px;
      margin-right: 0.5rem;
    }
  }

  .button:hover {
    background: linear-gradient(to right, #FFF8C5, #e6d8b0);
  }
`;
