import React, {Component} from 'react';
import Sensor from "../Sensor/Sensor";
import {Button} from "react-bootstrap";

export class SensorList extends Component {
    constructor(props) {
        super(props);
        this.state = {sensordata: []};
        this.refresh = this.refresh.bind(this)
    }

    refresh() {
        const requestOptions = {
            method: 'POST'
        };
        fetch('/control/refresh', requestOptions)
            .catch(console.log)
        fetch('/control/sensordata')
            .then(res => res.json())
            .then((data) => {
                this.setState({sensordata: data})
            })
            .catch(console.log)
    }

    componentDidMount() {
        this.refresh()
    }

    render() {
        return (
            <div>
                <h2>Sensoren</h2>
                <Sensor sensors={this.state.sensordata}/>
                <Button variant="primary" block onClick={this.refresh}>
                    Refresh
                </Button>
            </div>
        )
    }

}