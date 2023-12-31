import http from '../http-common'

class DataService {
  getAll() {
    return http.get('/employees')
  }

  get(id: number) {
    return http.get(`/employees/${id}`)
  }
}

export default new DataService()
