import React from 'react'
import {Table} from "react-bootstrap";
import Moment from 'moment';
import NumberFormat from 'react-number-format';
import './Sensor.css';

const Sensor = ({sensors}) => {
    return (

        <Table>
            <thead>
            <tr>
                <th  className="col-width" scope="col">Sensor</th>
                <th  className="col-width" scope="col">Aktuell (Absolut)</th>
                <th  className="col-width" scope="col">Aktuell %</th>
                <th  className="col-width" scope="col">Letzte Messung</th>
                <th  className="col-width" cope="col">Startet bei %</th>
            </tr>
            </thead>
            <tbody>
            {sensors.map((sensor) => (
                <tr key={sensor.name}>
                    <th  className="col-width" >{sensor.name}</th>
                    <th className="col-width" >{sensor.currentAbsolute}</th>
                    <th className="col-width" >
                        <NumberFormat allowLeadingZeros={true} value={Number((sensor.currentPercent * 100).toFixed(0))} displayType={'text'} suffix="%"/>
                    </th>
                    <th className="col-width" >{Moment(sensor.lastMeasurement).format('DD.MM.YYYY HH:mm')}</th>
                    <th className="col-width" >
                        <NumberFormat allowLeadingZeros={true} value={Number((sensor.toLow).toFixed(0))} displayType={'text'} suffix="%"/>
                    </th>
                </tr>
            ))}
            </tbody>
        </Table>
    )
};

export default Sensor