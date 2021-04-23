import React, {Component} from 'react';
import './Footer.css'
import {Accordion, Card} from "react-bootstrap";

export class Footer extends Component {

    render() {
        return (
            <Accordion className="footer">
                <Card>
                    <a className="center-text" href="https://github.com/fwidder">Sourcecode</a>
                    <a className="center-text">© Copyright Florian Widder {new Date().getFullYear()}</a>
                </Card>
            </Accordion>
        )
    }

}