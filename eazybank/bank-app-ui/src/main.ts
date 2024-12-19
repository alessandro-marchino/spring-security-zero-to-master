import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi, withXsrfConfiguration } from '@angular/common/http';
import { XhrInterceptor } from './app/interceptors';
import { AuthActivateRouteGuard } from './app/routeguards';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';
import { AppRoutingModule } from './app/app-routing.module';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';


bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(BrowserModule, AppRoutingModule, FormsModule),
    { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true },
    AuthActivateRouteGuard,
    provideHttpClient(withInterceptorsFromDi(), withXsrfConfiguration({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN',
    }))
  ]
}).catch(err => console.error(err));
