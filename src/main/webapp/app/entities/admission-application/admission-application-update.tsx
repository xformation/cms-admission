import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdmissionEnquiry } from 'app/shared/model/admission-enquiry.model';
import { getEntities as getAdmissionEnquiries } from 'app/entities/admission-enquiry/admission-enquiry.reducer';
import { getEntity, updateEntity, createEntity, reset } from './admission-application.reducer';
import { IAdmissionApplication } from 'app/shared/model/admission-application.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdmissionApplicationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAdmissionApplicationUpdateState {
  isNew: boolean;
  admissionEnquiryId: string;
}

export class AdmissionApplicationUpdate extends React.Component<IAdmissionApplicationUpdateProps, IAdmissionApplicationUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      admissionEnquiryId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getAdmissionEnquiries();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { admissionApplicationEntity } = this.props;
      const entity = {
        ...admissionApplicationEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/admission-application');
  };

  render() {
    const { admissionApplicationEntity, admissionEnquiries, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.admissionApplication.home.createOrEditLabel">Create or edit a AdmissionApplication</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : admissionApplicationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="admission-application-id">ID</Label>
                    <AvInput id="admission-application-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="sourceOfApplicationLabel" for="admission-application-sourceOfApplication">
                    Source Of Application
                  </Label>
                  <AvField id="admission-application-sourceOfApplication" type="text" name="sourceOfApplication" />
                </AvGroup>
                <AvGroup>
                  <Label id="studentIdLabel" for="admission-application-studentId">
                    Student Id
                  </Label>
                  <AvField id="admission-application-studentId" type="string" className="form-control" name="studentId" />
                </AvGroup>
                <AvGroup>
                  <Label id="applicationDateLabel" for="admission-application-applicationDate">
                    Application Date
                  </Label>
                  <AvField id="admission-application-applicationDate" type="date" className="form-control" name="applicationDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="completionDateLabel" for="admission-application-completionDate">
                    Completion Date
                  </Label>
                  <AvField id="admission-application-completionDate" type="date" className="form-control" name="completionDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="admissionNoLabel" for="admission-application-admissionNo">
                    Admission No
                  </Label>
                  <AvField id="admission-application-admissionNo" type="string" className="form-control" name="admissionNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="admissionDateLabel" for="admission-application-admissionDate">
                    Admission Date
                  </Label>
                  <AvField id="admission-application-admissionDate" type="date" className="form-control" name="admissionDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="admission-application-comments">
                    Comments
                  </Label>
                  <AvField id="admission-application-comments" type="text" name="comments" />
                </AvGroup>
                <AvGroup>
                  <Label id="applicationStatusLabel" for="admission-application-applicationStatus">
                    Application Status
                  </Label>
                  <AvField id="admission-application-applicationStatus" type="text" name="applicationStatus" />
                </AvGroup>
                <AvGroup>
                  <Label id="branchIdLabel" for="admission-application-branchId">
                    Branch Id
                  </Label>
                  <AvField id="admission-application-branchId" type="string" className="form-control" name="branchId" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdByLabel" for="admission-application-createdBy">
                    Created By
                  </Label>
                  <AvField id="admission-application-createdBy" type="text" name="createdBy" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdOnLabel" for="admission-application-createdOn">
                    Created On
                  </Label>
                  <AvField id="admission-application-createdOn" type="date" className="form-control" name="createdOn" />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="admission-application-updatedBy">
                    Updated By
                  </Label>
                  <AvField id="admission-application-updatedBy" type="text" name="updatedBy" />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedOnLabel" for="admission-application-updatedOn">
                    Updated On
                  </Label>
                  <AvField id="admission-application-updatedOn" type="date" className="form-control" name="updatedOn" />
                </AvGroup>
                <AvGroup>
                  <Label for="admission-application-admissionEnquiry">Admission Enquiry</Label>
                  <AvInput id="admission-application-admissionEnquiry" type="select" className="form-control" name="admissionEnquiryId">
                    <option value="" key="0" />
                    {admissionEnquiries
                      ? admissionEnquiries.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/admission-application" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  admissionEnquiries: storeState.admissionEnquiry.entities,
  admissionApplicationEntity: storeState.admissionApplication.entity,
  loading: storeState.admissionApplication.loading,
  updating: storeState.admissionApplication.updating,
  updateSuccess: storeState.admissionApplication.updateSuccess
});

const mapDispatchToProps = {
  getAdmissionEnquiries,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdmissionApplicationUpdate);
