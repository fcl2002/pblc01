import styled from "styled-components";

export const Card = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  gap: 20px;

  .card {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    padding: 1rem;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
    width: 200px;
    color: #fff;
    font-size: ${(props) => props.theme.fonts.p};

    h3 {
      font-weight: 200;
    }

    .icon {
      width: 20px;
      height: 20px;
      margin-bottom: 0.5rem;
    }

    .value {
      align-self: flex-end;
      font-size: ${(props) => props.theme.fonts.h3};
      font-weight: ${(props) => props.theme.fonts.bold};
    }
  }

  .divida {
    background: linear-gradient(to right, #e92121 0%, #2c39a7 100%);
  }

  .credito {
    background: linear-gradient(to right, #fe0098 0%, #7c42f8 100%);
  }
`;
