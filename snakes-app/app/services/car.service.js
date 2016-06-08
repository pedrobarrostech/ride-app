import {Injectable} from 'angular2/core';
import {SERVER_URL} from '../configs/consts';
import {Http, Headers, RequestOptions} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';


let apiURLReserve = SERVER_URL + 'cars/reservation';

@Injectable()
export class CarService {
     static get parameters() {
        return [[Http]];
     }

    constructor (http) {
        this.http = http;
    }

    findAll(initialDate, finalDate) {
        let apiURL = SERVER_URL + 'cars/reservation?initialDate=' + initialDate + '&finalDate='+ finalDate;
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(apiURL, options)
                .map(res => res.json())
                .catch(this.handleError);
    }

    findReservations() {
        let apiURL = SERVER_URL + 'cars/reservations';
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(apiURL, options)
                .map(res => res.json())
                .catch(this.handleError);
    }

    reserve(car) {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify({
        "licensePlate": car.licensePlate,
        "initialReservationDate": car.initialReservationDate,
        "finalReservationDate": car.finalReservationDate,
        "passengers": car.passengers,
        "destiny": car.destiny,
        "customer": car.customer
      });

      return this.http.post(apiURLReserve, body, options)
                  .map(res => res.json())
                  .catch(this.handleError);
    }

    reserveRide(car) {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });

      return this.http.put(SERVER_URL + `cars/reservation?reservation=${car.reservationId}&passengers=${car.passengers}`)
                  .map(res => res.json())
                  .catch(this.handleError);
    }

    cancelRide(car) {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });

      return this.http.put(SERVER_URL + `cars/reservation/cancel?reservation=${car.reservationId}&passengers=1`)
                  .map(res => res.json())
                  .catch(this.handleError);
    }

    cancelCar(car) {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });

      return this.http.get(SERVER_URL + `cars/reservation/${car.reservationId}/cancel`)
                  .map(res => res.json())
                  .catch(this.handleError);
    }

    handleError(error) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

    checkList(car, checkList) {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(checkList);

      return this.http.put(SERVER_URL + `cars/checklist/${car.licensePlate}`, body, options)
                  .map(res => res.json())
                  .catch(this.handleError);
    }

    damage(car, damage) {
      let headers = new Headers({ 'Content-Type': 'application/json' });
      let options = new RequestOptions({ headers: headers });
      let body = JSON.stringify(damage);

      return this.http.put(SERVER_URL + `cars/damage/${car.licensePlate}`, body, options)
                  .map(res => res.json())
                  .catch(this.handleError);
    }

}
