import axios from 'axios'

export default {
    getAllTasks: () =>
    axios({
            'method':'GET',
            'url':'http://localhost:8080/api/tasks',
            'headers': {
                'content-type':'application/json'
            },
    })
}