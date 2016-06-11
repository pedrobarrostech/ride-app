import {Page, NavController, ActionSheet, Modal} from 'ionic-angular';
import {CheckinPage} from '../checkin/checkin';
import {CarService} from '../../services/car.service';

@Page({
  templateUrl: 'build/pages/reservations/reservations.html',
  providers: [CarService],
})
export class ReservationsPage {

	static get parameters() {
		return [[NavController], [CarService]];
	}

  constructor(NavController, CarService) {
		this.carService = CarService;
		this.nav = NavController;
	}

	ngOnInit() {
    this.carService.findReservations().subscribe(response => {
      this.cars = response;
    });
  }

  freeCar(carParam) {
    this.carService.cancelRide(carParam).subscribe(response => {

    });
  }

  removeRide(carParam) {
    this.carService.cancelCar(carParam).subscribe(response => {
      this.cars = this.cars.filter((car) => {
        return (car.id !== carParam.id);
      });
    });
  }

  checkIn(car) {
    const modal = Modal.create(CheckinPage, { params: car });
    this.nav.present(modal);
  }
}
