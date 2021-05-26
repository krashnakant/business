import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AgencyManagerAppComponent} from './agency-manager-app.component';
import {MainContentComponent} from './components/main-content/main-content.component';


const routes: Routes = [
  {
    path: '', component: AgencyManagerAppComponent,
    children: [
      {path: '', component: MainContentComponent}
    ]
  },
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AgencyManagerRoutingModule {
}
