import React from 'react'
import {Table} from "react-bootstrap";
import Moment from 'moment';
import NumberFormat from 'react-number-format';

const Sensor = ({sensors}) => {
    return (

        <Table>
            <thead>
            <tr>
                <th scope="col">Sensor</th>
                <th scope="col">Aktuell (Absolut)</th>
                <th scope="col">Aktuell %</th>
                <th scope="col">Letzte Messung</th>
                <th scope="col">Startet bei %</th>
            </tr>
            </thead>
            <tbody>
            {sensors.map((sensor) => (
                <tr key={sensor.name}>
                    <th>{sensor.name}</th>
                    <th>{sensor.currentAbsolute}</th>
                    <th>
                        <NumberFormat allowLeadingZeros={true} value={Number((sensor.currentPercent * 100).toFixed(0))} displayType={'text'} suffix="%"/>
                    </th>
                    <th>{Moment(sensor.lastMeasurement).format('DD.MM.YYYY HH:mm')}</th>
                    <th>
                        <NumberFormat allowLeadingZeros={true} value={Number((sensor.toLow).toFixed(0))} displayType={'text'} suffix="%"/>
                    </th>
                </tr>
            ))}
            </tbody>
        </Table>
    )
};

export default Sensor