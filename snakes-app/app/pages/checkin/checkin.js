import {Page, Alert, NavController, ViewController, NavParams} from 'ionic-angular';
import {Dot} from '../../components/dot/dot';
import {CarService} from '../../services/car.service';
import {CarsListPage} from '../cars-list/cars-list';

let textID = 0;

@Page({
  templateUrl: 'build/pages/checkin/checkin.html',
  directives: [Dot],
  providers: [CarService],
})
export class CheckinPage {

	static get parameters() {
		return [[NavController], [ViewController], [CarService], [NavParams]];
	}

  constructor(NavController, ViewController, CarService, NavParams) {
		this.carService = CarService;
		this.view = ViewController;
    this.nav = NavController;

    textID = 0;

    this.carParam = NavParams.get("params");
    this.carHeight = document.body.offsetWidth - 30;
    this.damages = [];
    this.items = [
      {
        'id': 'radio',
        'text': 'Ap. de Som (Radio / Toca Fitas / CD)',
      },
      {
        'id': 'airConditioner',
        'text': 'Ar Condicionado',
      },
      {
        'id': 'lighter',
        'text': 'Acendedores',
      },
      {
        'id': 'wrench',
        'text': 'Chave de rodas',
      },
      {
        'id': 'powerLocks',
        'text': 'Trava Elétrica',
      },
      {
        'id': 'fireExtinguisher',
        'text': 'Extintor',
      },
      {
        'id': 'keysInMailBox',
        'text': 'Chaveiro',
      },
    ];
    this.checkListItems = {
      // 'radio': false,
      // 'airConditioner': false,
      // 'lighter': false,
      // 'wrench': false,
      // 'powerLocks': false,
      // 'fireExtinguisher': false,
      // 'keysInMailBox': false,

      "keysInMailBox":false,
      "radio":false,
      "airConditioner":true,
      "lighter":false,
      "wrench":false,
      "powerLocks":false,
      "fireExtinguisher":false,
      "keyRing":false,
      "antenna":false,
      "tires":false,
      "hubcaps":false,
      "spareTire":false,
      "triangle":false,
      "jack":false,
      "interior":false,
      "documents":false,
      "others":null,
      "checkListDate":1460859502992,
      "gasoline":null,
      "type":"IN",
      "userName":null
    };
  }

  handleClick(event) {
    console.log(event)
    let confirm = Alert.create({
      title: 'Descrição',
      message: 'Escreva uma descrição para o dano.',
      inputs: [
        {
          name: 'description',
          placeholder: 'Descrição'
        },
      ],
      buttons: [
        {
          text: 'Cancelar',
          handler: () => {
            console.log('Disagree clicked');
          }
        },
        {
          text: 'Salvar',
          handler: (data) => {
            textID += 1;
            let damage = {
              'left': `${event.layerX}px`,
              'top': `${event.layerY}px`,
              'text': textID,
              'description': data.description
            };
            this.damages.push(damage);
          }
        }
      ]
    });
    this.nav.present(confirm);
  }

  removeDamage(damageParam) {
    this.damages = this.damages.filter((damage) => {
      return (damage.text !== damageParam.text);
    });
  }

	cancel(){
		this.view.dismiss();
	}

  changeItem(event, item) {
    let isChecked = event.target.outerHTML.split('aria-che')[1].split('" aria-lab')[0].split('cked="')[1];
    this.checkListItems[item.id] = Boolean(isChecked);
  }

  save() {
    this.carService.checkList(this.carParam, this.checkListItems).subscribe(response => {
      this.carService.damage(this.carParam, this.damages).subscribe(response => {
        this.view.dismiss();
      });
    });
    console.log('ad');
  }
}
