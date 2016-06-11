import {Page, NavController} from 'ionic-angular';
import {TabsPage} from '../tabs/tabs';

@Page({
  templateUrl: 'build/pages/login/login.html'
})
export class LoginPage {
  static get parameters() {
    return [[NavController]];
  }

  constructor(nav) {
    this.nav = nav;
  }

  login() {
    // LOGIN MOCK
    window.localStorage.setItem('username', 'oi');
    this.nav.push(TabsPage);
    // var ref = window.open('https://app.cronofy.com/oauth/authorize/?response_type=code&client_id=83WiQOsPLapNb5ZN542VmB7o1nBlfs24&redirect_uri=http://172.28.212.13:8080/auth/response&scope=list_calendars read_events read_account', '_blank');
    // ref.addEventListener('loadstop', (event) => {
    //   if((event.url).indexOf("http://172.28.212.13") === 0) {
    //     // requestToken = (event.url).split("code=")[1];
    //     ref.close();
    //     window.localStorage.setItem('username', 'oi');
    //     this.nav.push(TabsPage);
    //   }
    // });
    // window.open('https://app.cronofy.com/oauth/authorize/?response_type=code&client_id=83WiQOsPLapNb5ZN542VmB7o1nBlfs24&redirect_uri=http://172.28.212.13:8080/auth/response&scope=list_calendars read_events read_account', '_blank');
  }
}
