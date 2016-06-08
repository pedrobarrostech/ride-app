import {Component} from 'angular2/core';

@Component({
  templateUrl: 'build/components/dot/dot.html',
  styles: [`
    .dot {
      position: absolute;
      width: 2.5rem;
      height: 2.5rem;
      line-height: 2.5rem;
      background-color: red;
      border-radius: 50%;
      color: #FFF;
      text-align: center;
      font-weight: bold;
    }
  `],
  selector: 'dot',
  'inputs': ['left', 'top', 'text'],
})
export class Dot {
  constructor() {
  }
}
