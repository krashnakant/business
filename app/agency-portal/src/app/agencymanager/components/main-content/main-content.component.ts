import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {EditAgencyDialogComponent} from '../edit-agency-dialog/edit-agency-dialog.component';
import {Agency} from "../../models/agency";
import {AgencyService} from "../../services/agency.service";


@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {

  @Input() agenciesData: Observable<Agency[]>;
  agency: Agency;

  constructor(
    private agencyService: AgencyService,
    private router: Router,
    private route: ActivatedRoute,
    private dialog: MatDialog) {
  }

  ngOnInit() {

    this.agenciesData = this.agencyService.agencies();
    this.agencyService.call();
  }

  openAgencyDialog(id: string, {name, countryCode, country, city, currency, contactPerson, street}: Agency) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "450px";
    dialogConfig.data = {id, name, countryCode, country, city, currency, contactPerson, street};
    const dialogRef = this.dialog.open(EditAgencyDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(
      val => console.log("Dialog output:", val)
    );
  }

  deleteItem(id: string) {
    this.agencyService.deleteAgency(id);
  }
}
