export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('user_free'));
    
    // Check if user and accessToken are available
    if (user && user.accessToken) {
        return {
            Authorization: 'Bearer ' + user.accessToken,
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        };
    } else {
        return {};
    }
}
