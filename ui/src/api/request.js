import axios from 'axios'
const instance = axios.create({
  // withCredentials: true,
  headers: {
    'Content-Type': 'application/json',
  },
  // timeout: 12000,
})



export default instance