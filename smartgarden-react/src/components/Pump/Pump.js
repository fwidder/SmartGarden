import React from 'react'
import {Table} from "react-bootstrap";
import Moment from 'moment';
import {Button} from "react-bootstrap";

const Pump = ({pumps, start, stop, activate, deactivate}) => {
    return (

        <Table>
            <thead>
            <tr>
                <th className="col-width" scope="col">Pumpe</th>
                <th className="col-width" scope="col">Status</th>
                <th className="col-width" scope="col">Deaktiviert</th>
                <th className="col-width" scope="col">Letzte Änderung</th>
                <th className="col-width" scope="col">Control</th>
                <th className="col-width" scope="col"/>
            </tr>
            </thead>
            <tbody>
            {pumps.map((pump) => (
                <tr key={pump.name}>
                    <th className="col-width">{pump.name}</th>
                    {pump.status &&
                    <th className="col-width">running</th>
                    }
                    {!pump.status &&
                    <th className="col-width">stopped</th>
                    }
                    {pump.disabled &&
                    <th className="col-width">Ja</th>
                    }
                    {!pump.disabled &&
                    <th className="col-width">Nein</th>
                    }
                    <th className="col-width">{Moment(pump.lastChange).format('DD.MM.YYYY HH:mm')}</th>
                    {pump.status &&
                    <th className="col-width"><Button className="col-width"
                                                      onClick={() => stop(pump.name)}>Stop</Button></th>
                    }
                    {!pump.status &&
                    <th className="col-width"><Button className="col-width"
                                                      onClick={() => start(pump.name)}>Start</Button></th>
                    }
                    {pump.disabled &&
                    <th className="col-width"><Button className="col-width"
                                                      onClick={() => activate(pump.name)}>Aktivieren</Button></th>
                    }
                    {!pump.disabled &&
                    <th className="col-width"><Button className="col-width"
                                                      onClick={() => deactivate(pump.name)}>Deaktivieren</Button></th>
                    }
                </tr>
            ))}
            </tbody>
        </Table>
    )
};

export default Pump