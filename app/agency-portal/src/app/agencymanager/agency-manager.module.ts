import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MaterialModule} from '../shared/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FlexLayoutModule} from '@angular/flex-layout';
import {AgencyManagerAppComponent} from './agency-manager-app.component';
import {ToolbarComponent} from './components/toolbar/toolbar.component';
import {MainContentComponent} from './components/main-content/main-content.component';
import {AgencyManagerRoutingModule} from './agency-manager-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {NewAgencyDialogComponent} from './components/new-agency-dialog/new-agency-dialog.component';
import {EditAgencyDialogComponent} from './components/edit-agency-dialog/edit-agency-dialog.component';

@NgModule({
  declarations: [
    AgencyManagerAppComponent,
    ToolbarComponent,
    MainContentComponent,
    NewAgencyDialogComponent,
    EditAgencyDialogComponent,

  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    HttpClientModule,
    AgencyManagerRoutingModule
  ],
  entryComponents: [NewAgencyDialogComponent, EditAgencyDialogComponent]
})
export class AgencyManagerModule {
}
