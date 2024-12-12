import styled from "styled-components";

export const Bar_title = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  margin-top: 2rem;

  .aux {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 100%;
  }

  p {
    color: #000;
    font-size: 1.5rem;
    margin: 1rem 0px;
    font-weight: bold;
  }

  button {
    padding: 0.5rem 2rem;
    background-color: ${(props) => props.theme.colors.background_button};
    border-radius: 4px;
    color: #fff;
    font-weight: ${(props) => props.theme.fonts.regular};
    cursor: pointer;
    border: none;
  }

  .line {
    width: 100%;
    height: 1px;
    background-color: ${(props) => props.theme.colors.label};
  }

  .bar_info > p {
    font-size: ${(props) => props.theme.fonts.p};
    font-weight: ${(props) => props.theme.fonts.regular};
    color: ${(props) => props.theme.colors.label};
  }
`;
