import BASE_URL from "./BaseUrl";

const MESSAGE_BASE_URL = `${BASE_URL}/api/v1/chat`;
const CHATROOM_BASE_URL = `${BASE_URL}/api/v1/chatroom`;

export const messageEndpoints = {
    getMessagesForChat: (chatId) => `${MESSAGE_BASE_URL}/${chatId}`,
    createChat: `${CHATROOM_BASE_URL}`,
    getAllChats: (username) => `${CHATROOM_BASE_URL}/${username}`,
};