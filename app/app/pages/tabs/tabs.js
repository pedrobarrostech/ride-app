import {Page} from 'ionic-angular';

import {CarsListPage} from '../cars-list/cars-list';
import {ContactPage} from '../contact/contact';
import {ReservationsPage} from '../reservations/reservations';
import {HomePage} from '../home/home';

@Page({
  templateUrl: 'build/pages/tabs/tabs.html'
})
export class TabsPage {
  constructor() {
    // this tells the tabs component which Pages
    // should be each tab's root Page
    this.tabHome = HomePage;
    this.tabCar = CarsListPage;
    this.tabReservations = ReservationsPage;
    this.tabContact = ContactPage;
  }
}
