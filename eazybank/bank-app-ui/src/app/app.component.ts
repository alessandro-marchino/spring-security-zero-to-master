import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from 'src/app/components';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    imports: [RouterOutlet, HeaderComponent]
})
export class AppComponent {
  title = 'bank-app-ui';
}
