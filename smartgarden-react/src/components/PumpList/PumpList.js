import React, {Component} from 'react';
import {Button} from "react-bootstrap";
import Pump from "../Pump/Pump";


export class PumpList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            pumpdata: [],
        };
        this.refresh = this.refresh.bind(this)
        this.timeout = this.timeout.bind(this)
        this.startPump = this.startPump.bind(this)
        this.stopPump = this.stopPump.bind(this)
        this.activatePump = this.activatePump.bind(this)
        this.deactivatePump = this.deactivatePump.bind(this)
    }

    timeout(delay) {
        return new Promise( res => setTimeout(res, delay) );
    }

    refresh() {
        fetch('http://localhost:8081/control/pumpdata')
            .then(res => res.json())
            .then((data) => {
                this.setState({pumpdata: data})
            })
            .catch(console.log)
    }

    async startPump(pump) {
        const requestOptions = {
            method: 'POST'
        };
        fetch(`http://localhost:8081/control/start/${pump}`, requestOptions)
            .catch(console.log)
        await this.timeout(1000);
        this.refresh()
    }

    async stopPump(pump) {
        const requestOptions = {
            method: 'POST'
        };
        fetch(`http://localhost:8081/control/stop/${pump}`, requestOptions)
            .catch(console.log)
        await this.timeout(1000);
        this.refresh()
    }

    async activatePump(pump) {
        const requestOptions = {
            method: 'POST'
        };
        fetch(`http://localhost:8081/control/activate/${pump}`, requestOptions)
            .catch(console.log)
        await this.timeout(1000);
        this.refresh()
    }

    async deactivatePump(pump) {
        const requestOptions = {
            method: 'POST'
        };
        fetch(`http://localhost:8081/control/deactivate/${pump}`, requestOptions)
            .catch(console.log)
        await this.timeout(1000);
        this.refresh()
    }

    componentDidMount() {
        this.refresh()
    }

    render() {
        return (
            <div>
                <h2>Pumpen</h2>
                <Pump pumps={this.state.pumpdata} start={this.startPump} stop={this.stopPump} activate={this.activatePump} deactivate={this.deactivatePump}/>
                <Button variant="primary" block onClick={this.refresh}>
                    Refresh
                </Button>
            </div>
        )
    }

}