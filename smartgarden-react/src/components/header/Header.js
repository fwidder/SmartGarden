import React, {Component} from 'react';
import {Nav, Navbar} from 'react-bootstrap';

export class Header extends Component {

    render() {
        return (
            <Navbar bg="light" expand="lg">
                <Navbar.Brand href="#home">Smart Garden</Navbar.Brand>
                <p className="center">
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto">
                        </Nav>
                    </Navbar.Collapse>
                </p>
            </Navbar>
        )
    }
}