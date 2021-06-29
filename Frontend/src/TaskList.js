import React from 'react'
import api from './Api'


export function TaskList() {
    const [taskList, setTaskList] = React.useState(null)

    const fetchData = () => {
        api.getAllTasks().then((response)=> {
            setTaskList(response.data)
            console.log(response.data)
        })
        .catch((error) => {
            console.log(error)
        })
    }

    if (taskList == null) {
        fetchData()
    }

    return (
        <div>
            {taskList && taskList.map(task => { return <p> {task.name} {' - '} {task.status}  </p>})}
        </div>
    )
}