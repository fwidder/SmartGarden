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
    }

    refresh() {
        fetch('/control/pumpdata')
            .then(res => res.json())
            .then((data) => {
                this.setState({pumpdata: data})
            })
            .catch(console.log)
    }

    componentDidMount() {
        this.refresh()
    }

    render() {
        return (
            <div>
                <h2>Pumpen</h2>
                <Pump pumps={this.state.pumpdata}></Pump>
                <Button variant="primary" block onClick={this.refresh}>
                    Refresh
                </Button>
            </div>
        )
    }

}