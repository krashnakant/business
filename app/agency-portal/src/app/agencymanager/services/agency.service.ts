import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs";
import {Agency} from "../models/agency";
import {HttpClient} from "@angular/common/http";
import {ApplicationConfig} from "./ApplicationConfig";


@Injectable({
  providedIn: 'root'
})
export class AgencyService {

  private applicationConfig: ApplicationConfig;

  private _agenciesSet: BehaviorSubject<Agency[]>;

  constructor(private http: HttpClient) {
    this.applicationConfig = new ApplicationConfig('http://localhost:8080/');
    this._agenciesSet = new BehaviorSubject<Agency[]>([]);
  }

  agencies(): Observable<Agency[]> {
    return this._agenciesSet.asObservable();
  }

  call() {
    this.getAgenciesSet().subscribe(data =>
      this._agenciesSet.next(data));
  }

  getAgenciesSet(): Observable<Agency[]> {
    return this.http.get<Agency[]>(this.applicationConfig.getEndpointFor('api/agencies'));
  }

  addAgency(agency: Agency) {
    this.http.post(this.applicationConfig.getEndpointFor('api/agencies'), agency).subscribe(error => {
      console.error('There was an error!', error);
      this.refreshAgencies();
    });
  }

  deleteAgency(id: string) {
    this.http.delete(this.applicationConfig.getEndpointFor(`api/agencies/${id}`)).subscribe(error => {
      console.error('There was an error!', error);
      this.refreshAgencies();
    });
  }

  updateAgency(id: string, fields: {}) {
    this.http.put(this.applicationConfig.getEndpointFor(`api/agencies/${id}`), fields).subscribe(
      error => {
        console.error('There was an error!', error);
        this.refreshAgencies();
      }
    );
  }

  refreshAgencies() {
    this.getAgenciesSet().subscribe(data =>
      this._agenciesSet.next(data));
  }
}
