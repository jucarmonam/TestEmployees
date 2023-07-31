import axios from 'axios'

export default axios.create({
  baseURL: 'http://127.0.0.1:8080/employesBackend-0.0.1-SNAPSHOT',
  headers: {
    'Content-type': 'application/json'
  }
})
