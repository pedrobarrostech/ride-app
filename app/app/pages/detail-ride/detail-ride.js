import {Page, ViewController, NavParams} from 'ionic-angular';

@Page({
  templateUrl: 'build/pages/detail-ride/detail-ride.html'
})
export class DetailRide {

	static get parameters() {
		return [[ViewController], [NavParams]];
	}

	constructor(view, params) {
		this.view = view;
    this.req = { licensePlate: params.get("params").licensePlate , initialReservationDate: params.get("initialReservationDate"), finalReservationDate: params.get("finalReservationDate"), passengers: "", destiny: "", customer: "" };
		this.car = params.get("params") || { name: "" };
  }

	cancel(){
		this.view.dismiss();
	}

	save(){
		this.view.dismiss(this.req);
	}
}
