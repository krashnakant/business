import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AgencyService} from "../../services/agency.service";
import {ICountry, ICurrency} from "../../models/SelectUtil";

@Component({
  selector: 'app-edit-employee-dialog',
  templateUrl: './edit-agency-dialog.component.html',
  styleUrls: ['./edit-agency-dialog.component.scss']
})
export class EditAgencyDialogComponent implements OnInit {
  @ViewChild('idvalue', {static: true}) idvalue: ElementRef;
  @ViewChild('indexvalue', {static: true}) indexvalue: ElementRef;
  form: FormGroup;

  currencyCode: ICurrency[] = [
    {value: 'GBP', viewValue: 'GBP'},
    {value: 'EUR', viewValue: 'EUR'},
    {value: 'USD', viewValue: 'USD'}
  ]

  countryCode: ICountry[] = [
    {value: 'FRA', viewValue: 'FRA'},
    {value: 'GBR', viewValue: 'FRA'},
    {value: 'USA', viewValue: 'USA'},
    {value: 'DEU', viewValue: 'DEU'},
    {value: 'ITA', viewValue: 'ITA'}
  ]

  constructor(private fb: FormBuilder,
              private dialogRef: MatDialogRef<EditAgencyDialogComponent>,
              private agencyService: AgencyService,
              @Inject(MAT_DIALOG_DATA) {id, name, countryCode, country, city, currency, contactPerson, street}) {

    this.form = fb.group({
      id: [id],
      name: [name],
      countryCode: [countryCode],
      country: [country],
      city: [city],
      currency: [currency],
      contactPerson: [contactPerson],
      street: [street],
    });
  }

  ngOnInit() {

  }

  onEdit() {

    this.agencyService.updateAgency(this.form.value.id, this.form.value);
    this.dialogRef.close();
  }

  onCancel() {
    this.dialogRef.close();
  }

}
