import BASE_URL from "./BaseUrl";

export const authEndpoints = {
  login: `${BASE_URL}/api/v1/member/login`,
  signup: `${BASE_URL}/api/v1/member/signup`,
  getMember: (username) => `${BASE_URL}/api/v1/member/${username}`,
};