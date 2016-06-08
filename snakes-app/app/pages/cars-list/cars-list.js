import {OnInit} from 'angular2/core';
import {Page, Modal, Alert, NavController} from 'ionic-angular';
import {CarService} from '../../services/car.service';
import {DetailCarModalPage} from '../detail-car/detail-car-modal';
import {DetailRide} from '../detail-ride/detail-ride';
import 'rxjs/add/operator/map';

@Page({
  templateUrl: 'build/pages/cars-list/cars-list.html',
  providers: [CarService]
})

export class CarsListPage {

	static get parameters() {
		return [[NavController], [CarService]];
	}

  constructor(NavController, CarService) {
		this.carService = CarService;
		this.nav = NavController;
    this.initialReservationDate = "";
    this.finalReservationDate = "";
    this.buttonDisabled = true;

	}

  checkInputs() {
    if (this.initialReservationDate !== '' && this.finalReservationDate !== '') {
      this.buttonDisabled = false;
    } else {
      this.buttonDisabled = true;
    }
  }

  filter(){
    this.carService.findAll(this.initialReservationDate, this.finalReservationDate).subscribe(response => {
      this.cars = response.filter((response) => {
        return (response.passengers === 0);
      });

      this.rides = response.filter((response) => {
        return (response.passengers !== 0);
      });
    });
  }

  list(){
    this.carService.findAll(this.initialReservationDate, this.finalReservationDate).subscribe(response => {
      this.cars = response.filter((response) => {
        return (response.passengers === 0);
      });

      this.rides = response.filter((response) => {
        return (response.passengers !== 0);
      });
    });
  }

	reserve(car, type) {
    var modal;

    if (type === 'car') {
      modal = Modal.create(DetailCarModalPage, { params: car, initialReservationDate: this.initialReservationDate, finalReservationDate: this.finalReservationDate });
    } else if (type === 'ride') {
      modal = Modal.create(DetailRide, { params: car, initialReservationDate: this.initialReservationDate, finalReservationDate: this.finalReservationDate });
    }

    modal.onDismiss(data => {
      // this.doAlert();

      if (type === 'car') {
        this.carService.reserve(data).subscribe();
      } else if (type === 'ride') {
        this.carService.reserveRide(car).subscribe();
      }
      console.log(data);
    });

		this.nav.present(modal);
	}

  doAlert() {
    let alert = Alert.create({
      title: 'New Friend!',
      message: 'Your friend, Obi wan Kenobi, just accepted your friend request!',
      buttons: ['Ok']
    });
    this.nav.present(alert);
  }
}
