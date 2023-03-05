//
// export const showModalError = (dispatch, message) => {
//     dispatchShowModal(dispatch, message, ERROR, 10000);
// };
//
// export const showModalSuccess = (dispatch, message, displayTime = 0) => {
//     dispatchShowModal(dispatch, message, SUCCESS, displayTime);
// };
//
// export const showModalEmailSendAndNotVerified = (dispatch, message, displayTime = 0) => {
//     dispatchShowModal(dispatch, message, ERROR, displayTime);
// };

//
// const dispatchShowModal = (dispatch, message, modalType, displayTime = 5000) => {
//     dispatch({
//         type: OPEN_MODAL,
//         data: {
//             type: modalType,
//             message,
//         },
//     });
//     if (displayTime !== 0) {
//         setTimeout(() => {
//             dispatchCloseModal(dispatch);
//         }, displayTime);
//     }
// };