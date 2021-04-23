import React from 'react'
import {Table} from "react-bootstrap";
import Moment from 'moment';
import {Button} from "react-bootstrap";

const Pump = ({pumps}) => {
    return (

        <Table>
            <thead>
            <tr>
                <th scope="col">Pumpe</th>
                <th scope="col">Status</th>
                <th scope="col">Letzte Änderung</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            {pumps.map((pump) => (
                <tr key={pump.name}>
                    <th>{pump.name}</th>
                    {pump.status &&
                    <th>running</th>
                    }
                    {!pump.status &&
                    <th>stopped</th>
                    }
                    <th>{Moment(pump.lastChange).format('DD.MM.YYYY HH:mm')}</th>
                    {pump.status &&
                    <th><Button>Stop</Button></th>
                    }
                    {!pump.status &&
                    <th><Button>Start</Button></th>
                    }
                </tr>
            ))}
            </tbody>
        </Table>
    )
};

export default Pump